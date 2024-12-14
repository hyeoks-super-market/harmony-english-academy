(function() {
    const PAGE_SIZE = 12;

    $(function () {
        listing(1, PAGE_SIZE);
    });

    function listing(page, size) {
        $.ajax({
            type: 'GET',
            url: '/api/v1/english-transcript?page=' + page + '&size=' + size,
            success: function (response) {
                if (response && response.content) {
                    renderTranscriptList(response.content);
                    renderPagination(response);
                } else {
                    console.error('올바르지 않은 응답 형식:', response);
                    alert('서버 응답이 올바르지 않습니다.');
                }
            },
            error: function (error) {
                console.error('영어 스크립트를 가져오는 중 오류 발생:', error);
                alert('영어 스크립트를 가져오는 중 오류가 발생했습니다. 서버 상태를 확인하세요.');
            }
        });
    }

    function renderTranscriptList(transcriptList) {
        const $articlesBox = $('#articles-box');
        $articlesBox.empty();

        const htmlArray = transcriptList.map(transcript => createTranscriptHtml(transcript));
        $articlesBox.append(htmlArray.join(''));
    }

    function createTranscriptHtml(transcript) {
        const { id, title, content, createdAt, youtubeUrl } = transcript;
        const truncatedContent = content.length > 100 ? content.substring(0, 100) + '...' : content;
        const formattedDate = new Date(createdAt).toLocaleDateString();
        const videoId = extractVideoId(youtubeUrl);
        const thumbnailUrl = videoId
            ? `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`
            : '/images/announce/noYoutubeVideo.png';

        const displayTitle = title || '게시글 정보 조회 불가';

        if (id) {
            return `
            <div class="col">
                <a href="detail.html?id=${encodeURIComponent(id)}" class="article-card h-100 text-decoration-none text-dark">
                    <img src="${thumbnailUrl}" class="article-img-top" alt="유튜브 영어 스크립트 게시글 이미지">
                    <div class="article-body">
                        <h5 class="article-title">${displayTitle}</h5>
                        <p class="article-text">${truncatedContent}</p>
                        <p class="article-date">게시일: ${formattedDate}</p>
                    </div>
                </a>
            </div>`;
        } else {
            return `
            <div class="col">
                <div class="article-card h-100">
                    <img src="${thumbnailUrl}" class="article-img-top" alt="유효하지 않은 영어 스크립트 게시글 안내 이미지">
                    <div class="article-body">
                        <h5 class="article-title text-muted">${displayTitle} <span class="text-danger">(유효하지 않은 게시글)</span></h5>
                        <p class="article-text">${truncatedContent}</p>
                        <p class="article-date">게시일: ${formattedDate}</p>
                    </div>
                </div>
            </div>`;
        }
    }

    function extractVideoId(youtubeUrl) {
        try {
            const urlObj = new URL(youtubeUrl);
            if (urlObj.hostname.includes('youtube.com')) {
                return urlObj.searchParams.get('v') || urlObj.pathname.split('/').pop();
            } else if (urlObj.hostname === 'youtu.be') {
                return urlObj.pathname.substring(1);
            }
        } catch (e) {
            console.error('유효하지 않은 YouTube URL:', youtubeUrl);
        }
        return null;
    }

    window.listing = listing;

})();
